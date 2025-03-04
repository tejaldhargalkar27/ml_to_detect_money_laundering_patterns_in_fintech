import pandas as pd
import numpy as np

def transaction_frequency_and_patterns(df):
    df['transaction_frequency_day'] = df.groupby('account_id')['transaction_id'].transform('count') / df['timestamp'].dt.days_in_month
    df['transaction_frequency_week'] = df.groupby('account_id')['transaction_id'].transform('count') / (df['timestamp'].dt.isocalendar().week)
    df['transaction_frequency_month'] = df.groupby('account_id')['transaction_id'].transform('count') / 12
    df['transaction_value_variability'] = df.groupby('account_id')['transaction_amount'].transform('std') / df.groupby('account_id')['transaction_amount'].transform('mean')
    return df

def average_transaction_amount(df):
    df['rolling_avg_txn_amount'] = df.groupby('account_id')['transaction_amount'].transform(lambda x: x.rolling(window=30, min_periods=1).mean())
    df['deviation_from_historical'] = df['transaction_amount'] - df['rolling_avg_txn_amount']
    return df

def geographical_indicators(df):
    high_risk_countries = ['CountryA', 'CountryB']
    df['cross_border_txn'] = df['country'].apply(lambda x: 1 if x not in high_risk_countries else 0)
    df['high_risk_country_txn'] = df['country'].apply(lambda x: 1 if x in high_risk_countries else 0)
    df['location_changes'] = df.groupby('account_id')['location'].transform(lambda x: x.nunique())
    return df

def counterparty_relationships(df):
    df['counterparty_network'] = df.groupby('counterparty_id')['transaction_id'].transform('count')
    df['abnormal_counterparty_activity'] = df['counterparty_network'].apply(lambda x: 1 if x > 100 else 0)
    df['first_time_txn'] = df.groupby('counterparty_id')['timestamp'].transform('min') == df['timestamp']
    return df

def risk_scores(df):
    df['historical_risk_score'] = df.groupby('account_id')['risk_score'].transform('mean')
    df['peer_comparison'] = df['transaction_amount'] / df.groupby('peer_group')['transaction_amount'].transform('mean')
    return df

def cash_flow_and_account_balances(df):
    df['net_cash_flow'] = df.groupby('account_id')['transaction_amount'].transform('sum')
    df['balance_change'] = df.groupby('account_id')['balance'].transform('diff')
    df['dormant_account_reactivation'] = df.groupby('account_id')['timestamp'].transform(lambda x: (x.diff().dt.days > 180).sum())
    return df

def velocity_of_transactions(df):
    df['txn_time_gap'] = df.groupby('account_id')['timestamp'].transform(lambda x: x.diff().dt.seconds)
    df['burst_txn_patterns'] = df['txn_time_gap'].apply(lambda x: 1 if x < 60 else 0)
    return df

def load_cleaned_data(file_path):
    return pd.read_csv(file_path)

def engineer_features(df):
    # Example feature engineering operations
    df['transaction_amount_log'] = df['normalized_booked_amount'].apply(lambda x: np.log1p(x))
    df['transaction_day'] = df['book_time'].apply(lambda x: pd.to_datetime(x).day)
    df['transaction_month'] = df['book_time'].apply(lambda x: pd.to_datetime(x).month)
    df['transaction_year'] = df['book_time'].apply(lambda x: pd.to_datetime(x).year)
    return df

def save_engineered_data(df, file_path):
    df.to_csv(file_path, index=False)

def main():
    # Load cleaned data
    cleaned_transaction_file = '../data/cleaned_transaction_table.csv'
    transaction_df = load_cleaned_data(cleaned_transaction_file)

    # Engineer features
    engineered_transaction_df = engineer_features(transaction_df)

    # Save engineered data
    engineered_transaction_file = '../data/engineered_transaction_table.csv'
    save_engineered_data(engineered_transaction_df, engineered_transaction_file)

if __name__ == "__main__":
    feature_engineered_data = engineer_features('data/cleaned_transactions.csv')
    feature_engineered_data.to_csv('data/engineered_features.csv', index=False)
