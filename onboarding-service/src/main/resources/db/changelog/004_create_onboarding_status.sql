CREATE TABLE IF NOT EXISTS onboarding.onboarding_status (
    status_id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL REFERENCES onboarding.customer(customer_id) ON DELETE CASCADE,
    registration_completed BOOLEAN DEFAULT FALSE,
    verification_completed BOOLEAN DEFAULT FALSE,
    kyc_completed BOOLEAN DEFAULT FALSE,
    profile_completed BOOLEAN DEFAULT FALSE,
    activated BOOLEAN DEFAULT FALSE,
    current_stage VARCHAR(50) DEFAULT 'REGISTERED',  -- ENUM: REGISTERED, VERIFIED, KYC_DONE, ACTIVE
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_onboarding_status_customer_id ON onboarding.onboarding_status(customer_id);