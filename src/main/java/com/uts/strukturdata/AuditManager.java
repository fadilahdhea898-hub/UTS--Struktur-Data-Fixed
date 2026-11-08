package com.uts.strukturdata;

public class AuditManager {
    private Stack<AuditLog> auditStack;

    public AuditManager() {
        this.auditStack = new Stack<>();
    }

    public void log(String aksi, String detail) {
        auditStack.push(new AuditLog(aksi, detail));
    }

    public void cetakAuditHistory() {
        System.out.println("\n=== RIWAYAT AUDIT SISTEM (STACK - LIFO) ===");
        Node<AuditLog> current = auditStack.getTop();
        if (current == null) {
            System.out.println("Belum ada riwayat audit.");
            return;
        }
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public Stack<AuditLog> getAuditStack() { return auditStack; }
}
