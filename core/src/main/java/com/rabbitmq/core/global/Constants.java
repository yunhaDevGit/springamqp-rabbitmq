package com.rabbitmq.core.global;


import com.google.common.collect.Sets;
import java.util.HashSet;

public class Constants {
  public static final String CLOUDIT_PATH = "/cloudit";
  public static final String ACTION_RESULT_PATH = CLOUDIT_PATH + "/result";

  public static final String COMPUTE_QUEUE = "compute-test";
  public static final String COMPUTE_EXCHANGE = "compute-exchange";
  public static final String NETWORK_QUEUE = "network-test";
  public static final String NETWORK_EXCHANGE = "network-exchange";


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
