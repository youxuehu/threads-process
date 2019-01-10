package com.gq.data.report.common.redis;


import java.util.ArrayList;
import java.util.List;

import org.redisson.config.BaseMasterSlaveServersConfig;
import org.redisson.config.SentinelServersConfig;

/**
 * Created by wyq_tomorrow on 2017/5/19.
 */
public class SentinelServersSelfConfig extends BaseMasterSlaveServersConfig<SentinelServersConfig> {
  private List<String> addresses = new ArrayList<>();
  private String masterName;

  public SentinelServersSelfConfig() {}

  public List<String> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<String> addresses) {
    this.addresses = addresses;
  }

  public String getMasterName() {
    return masterName;
  }

  public void setMasterName(String masterName) {
    this.masterName = masterName;
  }
}
