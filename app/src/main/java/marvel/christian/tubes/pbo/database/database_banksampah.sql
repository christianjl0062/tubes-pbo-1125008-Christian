CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    nama VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('NASABAH', 'PETUGAS', 'ADMIN')),
    saldo DOUBLE PRECISION DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS transaksi (
    id SERIAL PRIMARY KEY,
    username_nasabah VARCHAR(50) NOT NULL REFERENCES users(username) ON DELETE CASCADE,
    jenis_aktivitas VARCHAR(30) NOT NULL,
    detail_sampah VARCHAR(100) NOT NULL,
    nominal DOUBLE PRECISION NOT NULL,
    waktu TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, password, nama, role, saldo) VALUES
    ('nasabah', '123', 'Tono Budiman', 'NASABAH', 25000.0),
    ('nasabah2', '123', 'Melissa Permana', 'NASABAH', 10000.0),
    ('petugas', '123', 'Michael (Petugas)', 'PETUGAS', 0.0),
    ('admin', '123', 'Christian (Admin)', 'ADMIN', 0.0);

INSERT INTO transaksi (username_nasabah, jenis_aktivitas, detail_sampah, nominal) VALUES
    ('nasabah', 'SETOR_SAMPAH', 'Botol Plastik (2.0 kg)', 10000.0),
    ('nasabah', 'SETOR_SAMPAH', 'Kardus (5.0 kg)', 15000.0);