import pandas as pd

def load_cleaned_data(file_path):
    return pd.read_csv(file_path)

def aggregate_temporal_data(df):
    # Example temporal aggregation operations
    df['book_time'] = pd.to_datetime(df['book_time'])
    df.set_index('book_time', inplace=True)
    aggregated_df = df.resample('M').agg({
        'transaction_id': 'count',
        'normalized_booked_amount': 'sum'
    }).rename(columns={'transaction_id': 'transaction_count', 'normalized_booked_amount': 'total_amount'})
    return aggregated_df

def save_aggregated_data(df, file_path):
    df.to_csv(file_path)

def main():
    # Load cleaned data
    cleaned_transaction_file = '../data/cleaned_transaction_table.csv'
    transaction_df = load_cleaned_data(cleaned_transaction_file)

    # Aggregate temporal data
    aggregated_transaction_df = aggregate_temporal_data(transaction_df)

    # Save aggregated data
    aggregated_transaction_file = '../data/aggregated_transaction_table.csv'
    save_aggregated_data(aggregated_transaction_df, aggregated_transaction_file)

def define_time_windows(df):
    df['hourly_aggregation'] = df['transaction_amount'].resample('H').sum()
    df['daily_aggregation'] = df['transaction_amount'].resample('D').sum()
    df['monthly_aggregation'] = df['transaction_amount'].resample('M').sum()
    return df

def detect_anomalies(df):
    df['transaction_volume_comparison'] = df['transaction_amount'] / df.groupby('account_id')['transaction_amount'].transform('mean')
    df['repeating_transactions'] = df.groupby('account_id')['transaction_amount'].transform(lambda x: x.rolling(window=30).apply(lambda y: (y == y.iloc[0]).sum()))
    df['seasonal_trends'] = df['transaction_amount'].rolling(window=365).mean()
    return df

def rolling_window_analysis(df):
    df['rolling_avg'] = df['transaction_amount'].rolling(window=30).mean()
    df['exp_weighted_avg'] = df['transaction_amount'].ewm(span=30).mean()
    df['peak_detection'] = df['transaction_amount'].rolling(window=30).apply(lambda x: x.max())
    return df

def client_segmentation(df):
    df['high_risk_clients'] = df['transaction_amount'] > df['transaction_amount'].quantile(0.95)
    df['behavioral_drifts'] = df.groupby('account_id')['transaction_amount'].transform(lambda x: (x.diff().abs() > x.std()).sum())
    df['dormant_account_activation'] = df.groupby('account_id')['transaction_amount'].transform(lambda x: (x == 0).sum())
    return df

def temporal_aggregation(file_path):
    df = pd.read_csv(file_path, index_col='timestamp', parse_dates=True)
    df = define_time_windows(df)
    df = detect_anomalies(df)
    df = rolling_window_analysis(df)
    df = client_segmentation(df)
    return df

if __name__ == "__main__":
    aggregated_data = temporal_aggregation('data/transformed_transactions.csv')
    aggregated_data.to_csv('data/aggregated_transactions.csv', index=False)
