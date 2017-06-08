/*
 * Copyright (C) 2006 Google Inc.
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

package com.google.inject.example;

import com.google.inject.*;
import junit.framework.Assert;

/**
 * @author crazybob@google.com (Bob Lee)
 */
public class ClientServiceWithGuiceDefaults {

    // 44 lines

    @ImplementedBy(ServiceImpl.class)
    public interface Service {
        void go();
    }

    @Singleton
    public static class ServiceImpl implements Service {
        @Override
        public void go() {
            // ...
            System.out.println("ServiceImpl go ....");
        }
    }

    public static class Client {

        private final Service service;

        @Inject
        public Client(Service service) {
            this.service = service;
        }

        public void go() {
            service.go();
        }
    }

    public void testClient() {
        MockService mock = new MockService();
        Client client = new Client(mock);
        client.go();
        Assert.assertTrue(mock.isGone());
    }

    public static class MockService implements Service {

        private boolean gone = false;

        @Override
        public void go() {
            gone = true;
        }

        public boolean isGone() {
            return gone;
        }
    }

    public static void main(String[] args) throws CreationException {
        new ClientServiceWithGuiceDefaults().testClient();

        // 创建一个注入器
        Injector injector = Guice.createInjector();

        // 能够生成某种类型示例的 provider
        Provider<Client> provider = injector.getProvider(Client.class);
        Client client = provider.get();
        client.go();
    }
}
