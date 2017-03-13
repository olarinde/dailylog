package org.koweg.demo.dailylog.api.resource.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.koweg.demo.dailylog.api.model.Registration;
import org.koweg.demo.dailylog.api.model.Registrations;
import org.koweg.demo.dailylog.api.model.Subscription;
import org.koweg.demo.dailylog.api.model.Subscriptions;
import org.koweg.demo.dailylog.api.model.Subscription.ServiceType;
import org.koweg.demo.dailylog.api.resource.RegistrationsResource;

public class RegistrationsResourceImpl implements RegistrationsResource {
    private static Map<String, Registration> repository = new HashMap<String, Registration>();
    private static AtomicInteger id = new AtomicInteger(100);
    static{
        String  userId = String.valueOf(id.getAndIncrement());
        
        Set<String> currencyPairs = new HashSet<String>();
        currencyPairs.add("GBP");
        currencyPairs.add("USD");
        
        Set<Subscription> subscriptionSet = new HashSet<Subscription>();
        subscriptionSet.add((new Subscription()).withServiceType(ServiceType.CURR_LIVE_RATES).withCurrencyPairs(currencyPairs));
        
        Subscriptions subs = new Subscriptions().withSize(1).withSubscriptions(subscriptionSet);

        Registration reg = new Registration();
        reg.withEmail("jane.doe@test.com").withFirstname("Jane").withLastname("Doe").withUserId(userId).withSubscriptions(subs);
        
        repository.put(userId, reg);
        
    }

    public GetRegistrationsResponse getRegistrations(String xApiVersion, String serviceType) throws Exception {
        int size = repository.entrySet().size();
        List<Registration>regList = new ArrayList<Registration>(size);
        for (Entry<String, Registration>   entry : repository.entrySet()) {
            regList.add(entry.getValue());
        }
        Registrations registrations = new Registrations().withSize(size).withRegistrations(regList);
        return GetRegistrationsResponse.withJsonOK(registrations);
    }

    public PostRegistrationsResponse postRegistrations(String xApiVersion, Registration entity) throws Exception {
        String  userId = String.valueOf(id.getAndIncrement());
        repository.put(userId, entity.withUserId(userId));
        return PostRegistrationsResponse.withOK();
    }

}
