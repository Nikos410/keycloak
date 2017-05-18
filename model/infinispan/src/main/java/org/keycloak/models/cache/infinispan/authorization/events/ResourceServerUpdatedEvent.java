/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.models.cache.infinispan.authorization.events;

import org.keycloak.models.cache.infinispan.authorization.StoreFactoryCacheManager;
import org.keycloak.models.cache.infinispan.events.InvalidationEvent;

import java.util.Set;

/**
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class ResourceServerUpdatedEvent extends InvalidationEvent implements AuthorizationCacheInvalidationEvent {

    private String id;
    private String clientId;

    public static ResourceServerUpdatedEvent create(String id, String clientId) {
        ResourceServerUpdatedEvent event = new ResourceServerUpdatedEvent();
        event.id = id;
        event.clientId = clientId;
        return event;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("ResourceServerRemovedEvent [ id=%s, clientId=%s ]", id, clientId);
    }

    @Override
    public void addInvalidations(StoreFactoryCacheManager cache, Set<String> invalidations) {
        cache.resourceServerUpdated(id, clientId, invalidations);
    }
}
