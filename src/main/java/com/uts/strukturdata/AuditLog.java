package com.uts.strukturdata;

import java.time.LocalDateTime;

public class AuditLog {
    private String aksi;
    private String detail;
    private LocalDateTime timestamp;

    public AuditLog(String aksi, String detail) {
        this.aksi = aksi;
        this.detail = detail;
        this.timestamp = LocalDateTime.now();
    }

    public String getAksi() { return aksi; }
    public String getDetail() { return detail; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("[%s] AKSI: %s | DETAIL: %s", timestamp.toString(), aksi, detail);
    }
}
