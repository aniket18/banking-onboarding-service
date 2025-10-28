DO $enum$
BEGIN
    -- Create document_type_enum if it doesn't exist
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'document_type_enum') THEN
        CREATE TYPE document_type_enum AS ENUM (
            'AADHAAR',
            'PAN',
            'PASSPORT',
            'DRIVING_LICENSE',
            'VOTER_ID'
        );
    END IF;

    -- Create onboarding_stage_enum if it doesn't exist
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'onboarding_stage_enum') THEN
        CREATE TYPE onboarding_stage_enum AS ENUM (
            'REGISTERED',
            'VERIFICATION_PENDING',
            'VERIFIED',
            'KYC_DONE',
            'ACTIVE'
        );
    END IF;
END
$enum$;
