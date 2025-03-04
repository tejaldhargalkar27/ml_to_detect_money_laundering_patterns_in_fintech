import requests
import pandas as pd

def fetch_data_from_api(url):
    response = requests.get(url)
    response.raise_for_status()  # Raise an error for bad status codes
    return pd.DataFrame(response.json())

def clean_data(df):
    """Perform data cleaning operations on the DataFrame."""
    # Example cleaning operations
    df.dropna(inplace=True)  # Drop rows with missing values
    df['validity_start_time'] = pd.to_datetime(df['validity_start_time'])  # Convert to datetime
    df['is_entity_deleted'] = df['is_entity_deleted'].astype(bool)  # Convert to boolean

    # Add more cleaning operations as required
    return df

def save_clean_data(df, file_path):
    """Save the cleaned DataFrame to a CSV file."""
    df.to_csv(file_path, index=False)

def main():
    # Define API URLs
    base_url = 'http://localhost:8080/api'
    account_url = f'{base_url}/accounts'
    party_url = f'{base_url}/parties'
    party_supplementary_url = f'{base_url}/party-supplementary-data'
    risk_case_url = f'{base_url}/risk-case-events'
    transaction_url = f'{base_url}/transactions'

    # Fetch data from APIs
    account_df = fetch_data_from_api(account_url)
    party_df = fetch_data_from_api(party_url)
    party_supplementary_df = fetch_data_from_api(party_supplementary_url)
    risk_case_df = fetch_data_from_api(risk_case_url)
    transaction_df = fetch_data_from_api(transaction_url)

    # Clean data
    cleaned_account_df = clean_data(account_df)
    cleaned_party_df = clean_data(party_df)
    cleaned_party_supplementary_df = clean_data(party_supplementary_df)
    cleaned_risk_case_df = clean_data(risk_case_df)
    cleaned_transaction_df = clean_data(transaction_df)

    # Save cleaned data
    save_clean_data(cleaned_account_df, '../data/cleaned_account_table.csv')
    save_clean_data(cleaned_party_df, '../data/cleaned_party_table.csv')
    save_clean_data(cleaned_party_supplementary_df, '../data/cleaned_party_supplementary_data.csv')
    save_clean_data(cleaned_risk_case_df, '../data/cleaned_risk_case_event_table.csv')
    save_clean_data(cleaned_transaction_df, '../data/cleaned_transaction_table.csv')

if __name__ == "__main__":
    main()
