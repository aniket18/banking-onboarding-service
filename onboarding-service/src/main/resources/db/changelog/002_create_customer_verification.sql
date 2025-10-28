CREATE TABLE IF NOT EXISTS onboarding.customer_verification (
    verification_id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL REFERENCES onboarding.customer(customer_id) ON DELETE CASCADE,
    email_verified BOOLEAN DEFAULT FALSE,
    phone_verified BOOLEAN DEFAULT FALSE,
    kyc_verified BOOLEAN DEFAULT FALSE,
    verification_notes TEXT,
    verified_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_customer_verification_customer_id ON onboarding.customer_verification(customer_id);