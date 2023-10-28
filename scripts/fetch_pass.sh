#!/bin/bash

# Obtain HCP_API_TOKEN
HCP_API_TOKEN=$(curl --location 'https://auth.hashicorp.com/oauth/token' \
  --header 'content-type: application/json' \
  --data '{
    "audience": "https://api.hashicorp.cloud",
    "grant_type": "client_credentials",
    "client_id": "'$HCP_CLIENT_ID'",
    "client_secret": "'$HCP_CLIENT_SECRET'"
  }' | jq -r .access_token)

# Retrieve DB_PASS from HashiCorp Vault
DB_PASS=$(curl --location "https://api.cloud.hashicorp.com/secrets/2023-06-13/organizations/9975bd6f-ca79-4d0b-bd7a-d38e4bc01396/projects/1d0f38e2-c541-4756-86bc-6575c66deecc/apps/EmployeeApp/open" \
  --request GET \
  --header "Authorization: Bearer $HCP_API_TOKEN" | jq -r '.secrets[0]')

# Export the DB_PASS variable
export DB_PASS

# Optionally, you can print the value for verification
echo "DB_PASS: $DB_PASS"
