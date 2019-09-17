/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.journalkeeper.sql.server;

import io.journalkeeper.base.Serializer;
import io.journalkeeper.core.api.RaftServer;
import io.journalkeeper.core.api.StateFactory;
import io.journalkeeper.sql.client.domain.ReadRequest;
import io.journalkeeper.sql.client.domain.ReadResponse;
import io.journalkeeper.sql.client.domain.WriteRequest;
import io.journalkeeper.sql.client.domain.WriteResponse;
import io.journalkeeper.sql.serializer.KryoSerializer;
import io.journalkeeper.sql.state.SQLStateFactory;

import java.net.URI;
import java.util.List;
import java.util.Properties;

/**
 * SQLServerAccessPoint
 * author: gaohaoxiang
 *
 * date: 2019/6/10
 */
public class SQLServerAccessPoint {

    private Properties config;
    private StateFactory<WriteRequest, WriteResponse, ReadRequest, ReadResponse> stateFactory;
    private Serializer<WriteRequest> writeRequestSerializer;
    private Serializer<WriteResponse> writeResponseSerializer;
    private Serializer<ReadRequest> readRequestSerializer;
    private Serializer<ReadResponse> readResponseSerializer;

    public SQLServerAccessPoint(Properties config) {
        this(config,
                new SQLStateFactory(),
                new KryoSerializer<>(WriteRequest.class),
                new KryoSerializer<>(WriteResponse.class),
                new KryoSerializer<>(ReadRequest.class),
                new KryoSerializer<>(ReadResponse.class));
    }

    public SQLServerAccessPoint(Properties config,
                                StateFactory<WriteRequest, WriteResponse, ReadRequest, ReadResponse> stateFactory) {
        this(config,
                stateFactory,
                new KryoSerializer<>(WriteRequest.class),
                new KryoSerializer<>(WriteResponse.class),
                new KryoSerializer<>(ReadRequest.class),
                new KryoSerializer<>(ReadResponse.class));
    }

    public SQLServerAccessPoint(Properties config,
                                StateFactory<WriteRequest, WriteResponse, ReadRequest, ReadResponse> stateFactory,
                                Serializer<WriteRequest> writeRequestSerializer,
                                Serializer<WriteResponse> writeResponseSerializer,
                                Serializer<ReadRequest> readRequestSerializer,
                                Serializer<ReadResponse> readResponseSerializer) {
        this.config = config;
        this.stateFactory = stateFactory;
        this.writeRequestSerializer = writeRequestSerializer;
        this.writeResponseSerializer = writeResponseSerializer;
        this.readRequestSerializer = readRequestSerializer;
        this.readResponseSerializer = readResponseSerializer;
    }

    public SQLServer createServer(URI current, List<URI> servers, RaftServer.Roll role) {
        return new SQLServer(current, servers, config, role, stateFactory, writeRequestSerializer,
                writeResponseSerializer, readRequestSerializer, readResponseSerializer);
    }
}