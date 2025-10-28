CREATE TABLE IF NOT EXISTS onboarding.kyc_document (
    document_id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL REFERENCES onboarding.customer(customer_id) ON DELETE CASCADE,
    document_type VARCHAR(50) NOT NULL,  -- e.g., 'AADHAAR', 'PAN', 'PASSPORT'
    document_number VARCHAR(50),
    file_url VARCHAR(500),               -- where the doc is stored (S3, etc.)
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    verified BOOLEAN DEFAULT FALSE,
    verified_at TIMESTAMP,
    remarks TEXT
);
CREATE INDEX IF NOT EXISTS idx_kyc_document_customer_id ON onboarding.kyc_document(customer_id);