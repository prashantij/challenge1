
package com.psi.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.jcr.resource.api.JcrResourceConstants;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.settings.SlingSettingsService;

import com.day.cq.wcm.api.Page;


@Model(adaptables=SlingHttpServletRequest.class)
public class CurrencyTableModel {

    @Inject
    private SlingSettingsService settings;

    @Inject @Named(JcrResourceConstants.SLING_RESOURCE_TYPE_PROPERTY) @Default(values="No resourceType")
    protected String resourceType;

    @ScriptVariable(name="currentPage")
    private Page currentPage;
    
    private String currencyShortName;
    
    @ValueMapValue(name = "pageTitle",via = "resource",injectionStrategy = InjectionStrategy.REQUIRED)
    @Default(values="Currency")
     String pageTitle;

     public String getTitle() {
         return pageTitle;
     }
    
    @PostConstruct
    protected void init() {
    	
    	String pageName = currentPage.getName();
        if(pageName.equalsIgnoreCase("bitcoin")) {
        	currencyShortName = "BTC";
        }
        if(pageName.equalsIgnoreCase("litecoin")) {
        	currencyShortName = "LTC";
        }
        if(pageName.equalsIgnoreCase("ethereum")) {
        	currencyShortName = "ETH";
        }
        
    }

    public String getCurrencyShortName() {
    	return currencyShortName;
    }
    
}
