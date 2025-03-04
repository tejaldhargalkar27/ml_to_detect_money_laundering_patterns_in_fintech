import pandas as pd

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
