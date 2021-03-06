package org.xdi.oxd.server.service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author Yuriy Zabrovarnyy
 */

public class RpService {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(RpService.class);

    public static final String DEFAULT_SITE_CONFIG_JSON = "oxd-default-site-config.json";

    private final Map<String, Rp> rpMap = Maps.newConcurrentMap();

    private ValidationService validationService;

    private PersistenceService persistenceService;

    @Inject
    public RpService(ValidationService validationService, PersistenceService persistenceService) {
        this.validationService = validationService;
        this.persistenceService = persistenceService;
    }

    public void removeAllRps() {
        persistenceService.removeAllRps();
    }

    public void load() {
        for (Rp rp : persistenceService.getRps()) {
            put(rp);
        }
    }

    public Rp defaultRp() {
        Rp rp = rpMap.get(DEFAULT_SITE_CONFIG_JSON);
        if (rp == null) {
            LOG.error("Failed to load fallback configuration!");
            rp = new Rp();
        }
        return rp;
    }

    public Rp getRp(String id) {
        Preconditions.checkNotNull(id);
        Preconditions.checkState(!Strings.isNullOrEmpty(id));

        Rp site = rpMap.get(id);
        return validationService.validate(site);
    }

    public Map<String, Rp> getRps() {
        return Maps.newHashMap(rpMap);
    }

    public void update(Rp rp) throws IOException {
        put(rp);
        persistenceService.update(rp);
    }

    public void updateSilently(Rp rp) {
        try {
            update(rp);
        } catch (IOException e) {
            LOG.error("Failed to update site configuration: " + rp, e);
        }
    }

    public void create(Rp rp) throws IOException {
        if (StringUtils.isBlank(rp.getOxdId())) {
            rp.setOxdId(UUID.randomUUID().toString());
        }

        if (rpMap.get(rp.getOxdId()) == null) {
            put(rp);
            persistenceService.create(rp);
        } else {
            LOG.error("RP with already exists in database, oxd_id: " + rp.getOxdId());
        }
    }

    public Rp put(Rp rp) {
        return rpMap.put(rp.getOxdId(), rp);
    }
}
