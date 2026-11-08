package com.uts.strukturdata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PutusanTest {
    private AuditManager auditManager;
    private PutusanService service;

    @BeforeEach
    public void setUp() {
        auditManager = new AuditManager();
        service = new PutusanService(auditManager);
        
        // Add sample data
        service.tambahPutusan(new Putusan("101/Pid.Sus/2026/PN Jmr", "Terdakwa_01", "Narkotika", "Pasal 114 ayat (1)", 72, 60, 1000000000L, 3, "Sabu 0.5 gram", "Ditahan", "PN", "Hakim A", "Jaksa B", "2026-01-15", "Inkracht", "Verified"));
        service.tambahPutusan(new Putusan("102/Pid.Sus/2026/PN Jmr", "Terdakwa_02", "Narkotika", "Pasal 112 ayat (1)", 48, 48, 800000000L, 2, "Sabu 0.2 gram", "Ditahan", "PN", "Hakim C", "Jaksa D", "2026-01-20", "Inkracht", "Verified"));
        service.tambahPutusan(new Putusan("103/Pid.Sus/2026/PN Jmr", "Terdakwa_03", "Narkotika", "Pasal 127 ayat (1)", 24, 12, 0L, 0, "Ganja 1.0 gram", "Ditahan", "PN", "Hakim E", "Jaksa F", "2026-02-10", "Inkracht", "Verified"));
    }

    @Test
    public void testSinglyLinkedListSize() {
        assertEquals(3, service.getListPutusan().getSize());
    }

    @Test
    public void testCariByNomorPerkaraNonDestruktif() {
        Putusan p = service.cariByNomorPerkara("102/Pid.Sus/2026/PN Jmr");
        assertNotNull(p);
        assertEquals("Terdakwa_02", p.getNamaTerdakwa());
        // Verify size did not change
        assertEquals(3, service.getListPutusan().getSize());
    }

    @Test
    public void testHapusPutusan() {
        boolean removed = service.hapusPutusan("101/Pid.Sus/2026/PN Jmr");
        assertTrue(removed);
        assertEquals(2, service.getListPutusan().getSize());
        assertNull(service.cariByNomorPerkara("101/Pid.Sus/2026/PN Jmr"));
    }

    @Test
    public void testStackAuditLog() {
        AuditLog topLog = auditManager.getAuditStack().peek();
        assertNotNull(topLog);
        assertEquals("TAMBAH_PUTUSAN", topLog.getAksi());
    }

    @Test
    public void testPengurutanVonis() {
        service.urutkanByVonis(true); // Ascending: 12, 48, 60
        Node<Putusan> head = service.getListPutusan().getHead();
        assertEquals(12, head.getData().getVonisBulan());
        assertEquals(60, service.getListPutusan().getHead().getNext().getNext().getData().getVonisBulan());
    }
}
