import pandas as pd
from sklearn.impute import SimpleImputer
from sklearn.neighbors import KNeighborsRegressor

def handle_missing_values(df):
    # Categorize missing data
    mcar = df.isna().sum() / len(df)
    mar = df[df.columns[df.isna().sum() > 0.05]]
    
    # Imputation techniques
    imputer_mean_median = SimpleImputer(strategy='mean')
    imputer_mode = SimpleImputer(strategy='most_frequent')
    
    df['transaction_amount'] = imputer_mean_median.fit_transform(df[['transaction_amount']])
    df['transaction_type'] = imputer_mode.fit_transform(df[['transaction_type']])
    df['account_status'] = imputer_mode.fit_transform(df[['account_status']])
    
    # Regression-based imputation
    knn_imputer = KNeighborsRegressor(n_neighbors=5)
    df['account_balance'] = df['account_balance'].fillna(knn_imputer.fit_transform(df[['transaction_frequency', 'customer_income']])[:, 0])
    
    # Handle missing timestamps
    df['timestamp'] = df['timestamp'].fillna(method='ffill').fillna(method='bfill')
    
    return df

def remove_duplicates(df):
    df.drop_duplicates(subset=['transaction_id', 'account_id', 'timestamp'], inplace=True)
    return df

def standardize_format(df):
    # Standardize date formats
    df['timestamp'] = pd.to_datetime(df['timestamp'])
    # Standardize numeric precision
    df = df.round(2)
    # Standardize category fields
    df['currency_code'] = df['currency_code'].str.upper()
    return df

def mask_data_entry_errors(df):
    # Identify and correct errors
    df['account_number'] = df['account_number'].apply(lambda x: x if len(str(x)) == 10 else None)
    df['transaction_type'] = df['transaction_type'].apply(lambda x: x if x in ['DEBIT', 'CREDIT'] else None)
    return df

def clean_data(file_path):
    df = pd.read_csv(file_path)
    df = handle_missing_values(df)
    df = remove_duplicates(df)
    df = standardize_format(df)
    df = mask_data_entry_errors(df)
    return df

if __name__ == "__main__":
    cleaned_data = clean_data('data/transactions.csv')
    cleaned_data.to_csv('data/cleaned_transactions.csv', index=False)
