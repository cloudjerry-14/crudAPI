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

# Retrieve DB_PASS from HashiCorp Vault and export the variable
DB_PASS=$(curl --location "https://api.cloud.hashicorp.com/secrets/2023-06-13/organizations/9975bd6f-ca79-4d0b-bd7a-d38e4bc01396/projects/1d0f38e2-c541-4756-86bc-6575c66deecc/apps/EmployeeApp/open" \
  --request GET \
  --header "Authorization: Bearer $HCP_API_TOKEN" | jq -r '.secrets[0]')

# Assuming DB_PASS contains the JSON structure
password=$(echo "$DB_PASS" | jq -r '.version.value')
echo "Password extracted from JSON: $password"

# Export the password as an environment variable
export DB_PASSWORD=$password

echo "exported"

echo "password exported is :  $DB_PASSWORD"



