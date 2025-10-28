CREATE TABLE IF NOT EXISTS onboarding.audit_log (
    audit_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES onboarding.customer(customer_id),
    action VARCHAR(100),       -- e.g., 'REGISTER', 'VERIFY', 'UPLOAD_DOC'
    performed_by VARCHAR(100), -- system / user id
    action_details JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_audit_log_customer_id ON onboarding.audit_log(customer_id);