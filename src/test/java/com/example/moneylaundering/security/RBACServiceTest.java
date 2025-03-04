package com.example.moneylaundering.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RBACServiceTest {

    private RBACService rbacService;

    @BeforeEach
    void setUp() {
        rbacService = new RBACService();
    }

    @Test
    void testHasAccess() {
        assertTrue(rbacService.hasAccess("complianceOfficer", "COMPLIANCE_OFFICER"));
        assertFalse(rbacService.hasAccess("complianceOfficer", "RISK_ANALYST"));
    }
}
