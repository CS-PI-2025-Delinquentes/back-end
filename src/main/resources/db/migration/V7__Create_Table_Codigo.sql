CREATE TABLE IF NOT EXISTS "codigo" (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(6) NOT NULL,
    pessoa_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NOT NULL,
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id) ON DELETE CASCADE
);

CREATE INDEX idx_codigo_pessoa_id ON codigo(pessoa_id);
CREATE INDEX idx_codigo_expires_at ON codigo(expires_at);