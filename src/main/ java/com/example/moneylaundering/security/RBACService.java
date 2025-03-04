package com.example.moneylaundering.security;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RBACService {

    private Map<String, String> userRoles;

    public RBACService() {
        userRoles = new HashMap<>();
        // Example roles
        userRoles.put("complianceOfficer", "COMPLIANCE_OFFICER");
        userRoles.put("riskAnalyst", "RISK_ANALYST");
        userRoles.put("mlEngineer", "ML_ENGINEER");
    }

    public String getUserRole(String username) {
        return userRoles.get(username);
    }

    public boolean hasAccess(String username, String requiredRole) {
        String userRole = getUserRole(username);
        return userRole != null && userRole.equals(requiredRole);
    }
}
