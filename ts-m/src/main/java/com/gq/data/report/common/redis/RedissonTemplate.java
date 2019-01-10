package com.gq.data.report.common.redis;

import java.util.concurrent.TimeUnit;

import org.redisson.api.*;
import org.redisson.client.codec.Codec;
import org.redisson.codec.CodecProvider;
import org.redisson.config.Config;
import org.redisson.liveobject.provider.ResolverProvider;

public class RedissonTemplate implements RedissonClient {


  private RedissonClient redisson;

  @Override
  public RBinaryStream getBinaryStream(String name) {
    return null;
  }

  @Override
  public <V> RGeo<V> getGeo(String s) {
    return redisson.getGeo(s);
  }

  @Override
  public <V> RGeo<V> getGeo(String s, Codec codec) {
    return redisson.getGeo(s, codec);
  }

  @Override
  public <V> RSetCache<V> getSetCache(String s) {
    return redisson.getSetCache(s);
  }

  @Override
  public <V> RSetCache<V> getSetCache(String s, Codec codec) {
    return redisson.getSetCache(s, codec);
  }

  @Override
  public <K, V> RMapCache<K, V> getMapCache(String s, Codec codec) {
    return redisson.getMapCache(s, codec);
  }

  @Override
  public <K, V> RMapCache<K, V> getMapCache(String name, Codec codec, MapOptions<K, V> options) {
    return null;
  }

  @Override
  public <K, V> RMapCache<K, V> getMapCache(String s) {
    return redisson.getMapCache(s);
  }

  @Override
  public <K, V> RMapCache<K, V> getMapCache(String name, MapOptions<K, V> options) {
    return null;
  }

  @Override
  public <V> RBucket<V> getBucket(String s) {
    return redisson.getBucket(s);
  }

  @Override
  public <V> RBucket<V> getBucket(String s, Codec codec) {
    return redisson.getBucket(s, codec);
  }

  @Override
  public RBuckets getBuckets() {
    return redisson.getBuckets();
  }

  @Override
  public RBuckets getBuckets(Codec codec) {
    return redisson.getBuckets(codec);
  }





  @Override
  public <V> RHyperLogLog<V> getHyperLogLog(String s) {
    return redisson.getHyperLogLog(s);
  }

  @Override
  public <V> RHyperLogLog<V> getHyperLogLog(String s, Codec codec) {
    return redisson.getHyperLogLog(s, codec);
  }

  @Override
  public <V> RList<V> getList(String s) {
    return redisson.getList(s);
  }

  @Override
  public <V> RList<V> getList(String s, Codec codec) {
    return redisson.getList(s, codec);
  }

  @Override
  public <K, V> RListMultimap<K, V> getListMultimap(String s) {
    return redisson.getListMultimap(s);
  }

  @Override
  public <K, V> RListMultimap<K, V> getListMultimap(String s, Codec codec) {
    return redisson.getListMultimap(s, codec);
  }

  @Override
  public <K, V> RListMultimapCache<K, V> getListMultimapCache(String s) {
    return redisson.getListMultimapCache(s);
  }

  @Override
  public <K, V> RListMultimapCache<K, V> getListMultimapCache(String s, Codec codec) {
    return redisson.getListMultimapCache(s, codec);
  }

  @Override
  public <K, V> RLocalCachedMap<K, V> getLocalCachedMap(String name, LocalCachedMapOptions<K, V> options) {
    return null;
  }

  @Override
  public <K, V> RLocalCachedMap<K, V> getLocalCachedMap(String name, Codec codec, LocalCachedMapOptions<K, V> options) {
    return null;
  }

  @Override
  public <K, V> RMap<K, V> getMap(String s) {
    return redisson.getMap(s);
  }

  @Override
  public <K, V> RMap<K, V> getMap(String name, MapOptions<K, V> options) {
    return null;
  }

  @Override
  public <K, V> RMap<K, V> getMap(String s, Codec codec) {
    return redisson.getMap(s, codec);
  }

  @Override
  public <K, V> RMap<K, V> getMap(String name, Codec codec, MapOptions<K, V> options) {
    return null;
  }

  @Override
  public <K, V> RSetMultimap<K, V> getSetMultimap(String s) {
    return redisson.getSetMultimap(s);
  }

  @Override
  public <K, V> RSetMultimap<K, V> getSetMultimap(String s, Codec codec) {
    return redisson.getSetMultimap(s, codec);
  }

  @Override
  public <K, V> RSetMultimapCache<K, V> getSetMultimapCache(String s) {
    return redisson.getSetMultimapCache(s);
  }

  @Override
  public <K, V> RSetMultimapCache<K, V> getSetMultimapCache(String s, Codec codec) {
    return redisson.getSetMultimapCache(s, codec);
  }

  @Override
  public RSemaphore getSemaphore(String s) {
    return redisson.getSemaphore(s);
  }

  @Override
  public RPermitExpirableSemaphore getPermitExpirableSemaphore(String name) {
    return null;
  }

  @Override
  public RLock getLock(String s) {
    return redisson.getLock(s);
  }

  @Override
  public RLock getFairLock(String s) {
    return redisson.getFairLock(s);
  }

  @Override
  public RReadWriteLock getReadWriteLock(String s) {
    return redisson.getReadWriteLock(s);
  }

  @Override
  public <V> RSet<V> getSet(String s) {
    return redisson.getSet(s);
  }

  @Override
  public <V> RSet<V> getSet(String s, Codec codec) {
    return redisson.getSet(s, codec);
  }

  @Override
  public <V> RSortedSet<V> getSortedSet(String s) {
    return redisson.getSortedSet(s);
  }

  @Override
  public <V> RSortedSet<V> getSortedSet(String s, Codec codec) {
    return redisson.getSortedSet(s, codec);
  }

  @Override
  public <V> RScoredSortedSet<V> getScoredSortedSet(String s) {
    return redisson.getScoredSortedSet(s);
  }

  @Override
  public <V> RScoredSortedSet<V> getScoredSortedSet(String s, Codec codec) {
    return redisson.getScoredSortedSet(s, codec);
  }

  @Override
  public RLexSortedSet getLexSortedSet(String s) {
    return redisson.getLexSortedSet(s);
  }

  @Override
  public <M> RTopic<M> getTopic(String s) {
    return redisson.getTopic(s);
  }

  @Override
  public <M> RTopic<M> getTopic(String s, Codec codec) {
    return redisson.getTopic(s, codec);
  }

  @Override
  public <M> RPatternTopic<M> getPatternTopic(String s) {
    return redisson.getPatternTopic(s);
  }

  @Override
  public <M> RPatternTopic<M> getPatternTopic(String s, Codec codec) {
    return redisson.getPatternTopic(s, codec);
  }

  @Override
  public <V> RQueue<V> getQueue(String s) {
    return redisson.getQueue(s);
  }

  @Override
  public <V> RDelayedQueue<V> getDelayedQueue(RQueue<V> destinationQueue) {
    return null;
  }

  @Override
  public <V> RQueue<V> getQueue(String s, Codec codec) {
    return redisson.getQueue(s, codec);
  }

  @Override
  public <V> RPriorityQueue<V> getPriorityQueue(String name) {
    return null;
  }

  @Override
  public <V> RPriorityQueue<V> getPriorityQueue(String name, Codec codec) {
    return null;
  }

  @Override
  public <V> RPriorityDeque<V> getPriorityDeque(String name) {
    return null;
  }

  @Override
  public <V> RPriorityDeque<V> getPriorityDeque(String name, Codec codec) {
    return null;
  }

  @Override
  public <V> RBlockingQueue<V> getBlockingQueue(String s) {
    return redisson.getBlockingQueue(s);
  }

  @Override
  public <V> RBlockingQueue<V> getBlockingQueue(String s, Codec codec) {
    return redisson.getBlockingQueue(s, codec);
  }

  @Override
  public <V> RBoundedBlockingQueue<V> getBoundedBlockingQueue(String name) {
    return null;
  }

  @Override
  public <V> RBoundedBlockingQueue<V> getBoundedBlockingQueue(String name, Codec codec) {
    return null;
  }

  @Override
  public <V> RDeque<V> getDeque(String s) {
    return redisson.getDeque(s);
  }

  @Override
  public <V> RDeque<V> getDeque(String s, Codec codec) {
    return redisson.getDeque(s, codec);
  }

  @Override
  public <V> RBlockingDeque<V> getBlockingDeque(String s) {
    return redisson.getBlockingDeque(s);
  }

  @Override
  public <V> RBlockingDeque<V> getBlockingDeque(String s, Codec codec) {
    return redisson.getBlockingDeque(s, codec);
  }

  @Override
  public RAtomicLong getAtomicLong(String s) {
    return redisson.getAtomicLong(s);
  }

  @Override
  public RAtomicDouble getAtomicDouble(String s) {
    return redisson.getAtomicDouble(s);
  }

  @Override
  public RCountDownLatch getCountDownLatch(String s) {
    return redisson.getCountDownLatch(s);
  }

  @Override
  public RBitSet getBitSet(String s) {
    return redisson.getBitSet(s);
  }

  @Override
  public <V> RBloomFilter<V> getBloomFilter(String s) {
    return redisson.getBloomFilter(s);
  }

  @Override
  public <V> RBloomFilter<V> getBloomFilter(String s, Codec codec) {
    return redisson.getBloomFilter(s, codec);
  }

  @Override
  public RScript getScript() {
    return redisson.getScript();
  }

  @Override
  public RScheduledExecutorService getExecutorService(String name) {
    return null;
  }

  @Override
  public RScheduledExecutorService getExecutorService(Codec codec, String name) {
    return null;
  }

  @Override
  public RScheduledExecutorService getExecutorService(String name, Codec codec) {
    return null;
  }

  @Override
  public RRemoteService getRemoteService() {
    return null;
  }

  @Override
  public RRemoteService getRemoteService(Codec codec) {
    return null;
  }

  @Override
  public RRemoteService getRemoteService(String name) {
    return null;
  }

  @Override
  public RRemoteService getRemoteService(String name, Codec codec) {
    return null;
  }


  @Override
  public RBatch createBatch() {
    return redisson.createBatch();
  }

  @Override
  public RKeys getKeys() {
    return redisson.getKeys();
  }

  @Override
  public RLiveObjectService getLiveObjectService() {
    return null;
  }

  @Override
  public void shutdown() {
    redisson.shutdown();
  }

  @Override
  public void shutdown(long l, long l1, TimeUnit timeUnit) {
    redisson.shutdown(l, l1, timeUnit);
  }

  @Override
  public Config getConfig() {
    return redisson.getConfig();
  }

  @Override
  public CodecProvider getCodecProvider() {
    return null;
  }

  @Override
  public ResolverProvider getResolverProvider() {
    return null;
  }

  @Override
  public NodesGroup<Node> getNodesGroup() {
    return redisson.getNodesGroup();
  }

  @Override
  public ClusterNodesGroup getClusterNodesGroup() {
    return redisson.getClusterNodesGroup();
  }

  @Override
  public boolean isShutdown() {
    return false;
  }

  @Override
  public boolean isShuttingDown() {
    return false;
  }

  public void setRedisson(RedissonClient redisson) {
    this.redisson = redisson;
  }


}
