/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.journalkeeper.monitor;

import java.net.URI;

/**
 * Leader节点上记录的Follower信息
 * @author LiYue
 * Date: 2019/11/19
 */
public class LeaderFollowerMonitorInfo {
    // LEADER的从节点URI	当前LEADER节点记录的FOLLOWER节点的URI
    private URI uri = null;
    // 下一次复制索引序号	需要发给FOLLOWER的下一个日志条目的索引（初始化为领导人上一条日志的索引值 +1）
    private long nextIndex = -1L;
    // 已复制索引序号	已经复制到该FOLLOWER的日志的最高索引值（从 0 开始递增）
    private long matchIndex = -1L;
    // 心跳响应时间	上次从FOLLOWER收到心跳（asyncAppendEntries）成功响应的时间戳
    private long lastHeartbeatResponseTime = -1L;
    // 心跳发送时间	上次发给FOLLOWER心跳（asyncAppendEntries）的时间戳
    private long lastHeartbeatRequestTime = -1L;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public long getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(long nextIndex) {
        this.nextIndex = nextIndex;
    }

    public long getMatchIndex() {
        return matchIndex;
    }

    public void setMatchIndex(long matchIndex) {
        this.matchIndex = matchIndex;
    }

    public long getLastHeartbeatResponseTime() {
        return lastHeartbeatResponseTime;
    }

    public void setLastHeartbeatResponseTime(long lastHeartbeatResponseTime) {
        this.lastHeartbeatResponseTime = lastHeartbeatResponseTime;
    }

    public long getLastHeartbeatRequestTime() {
        return lastHeartbeatRequestTime;
    }

    public void setLastHeartbeatRequestTime(long lastHeartbeatRequestTime) {
        this.lastHeartbeatRequestTime = lastHeartbeatRequestTime;
    }

    @Override
    public String toString() {
        return "LeaderFollowerMonitorInfo{" +
                "uri=" + uri +
                ", nextIndex=" + nextIndex +
                ", matchIndex=" + matchIndex +
                ", lastHeartbeatResponseTime=" + lastHeartbeatResponseTime +
                ", lastHeartbeatRequestTime=" + lastHeartbeatRequestTime +
                '}';
    }
}
