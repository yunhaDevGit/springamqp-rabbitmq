package com.rabbitmq.core.global;


import com.google.common.collect.Sets;
import java.util.HashSet;

public class Constants {

  public enum SERVICE_TYPE {
    PRODUCER_API,
    CONSUMER_API
  }
  public enum EVENT_CODE {
    VM_CREATE(SERVICE_TYPE.PRODUCER_API),
    VM_MULTI_CREATE(SERVICE_TYPE.CONSUMER_API);

    private HashSet<SERVICE_TYPE> _serviceType;

    EVENT_CODE(SERVICE_TYPE... serviceType) {
      _serviceType = Sets.newHashSet(serviceType);
    }

    public HashSet<SERVICE_TYPE> getServiceType() {
      return _serviceType;
    }

    public boolean checkServiceType(SERVICE_TYPE serviceType) {
      return _serviceType.contains(serviceType);
    }

  }

}
