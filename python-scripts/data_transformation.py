import pandas as pd
from sklearn.preprocessing import OneHotEncoder, LabelEncoder, MinMaxScaler, StandardScaler
import numpy as np

def encode_categorical_variables(df):
    # One-Hot Encoding
    one_hot_encoder = OneHotEncoder(sparse=False)
    transaction_types = one_hot_encoder.fit_transform(df[['transaction_type']])
    df = df.join(pd.DataFrame(transaction_types, columns=one_hot_encoder.get_feature_names_out(['transaction_type'])))
    
    # Label Encoding
    label_encoder = LabelEncoder()
    df['transaction_direction'] = label_encoder.fit_transform(df['transaction_direction'])
    
    # Frequency Encoding
    freq_encoding = df.groupby('transaction_purpose').size() / len(df)
    df['transaction_purpose_freq'] = df['transaction_purpose'].map(freq_encoding)
    
    return df

def scale_numerical_features(df):
    # Min-Max Scaling
    min_max_scaler = MinMaxScaler()
    df[['transaction_amount', 'account_balance']] = min_max_scaler.fit_transform(df[['transaction_amount', 'account_balance']])
    
    # Z-Score Standardization
    standard_scaler = StandardScaler()
    df[['transaction_frequency', 'account_turnover_rate']] = standard_scaler.fit_transform(df[['transaction_frequency', 'account_turnover_rate']])
    
    return df

def log_transform_skewed_data(df):
    df['log_transaction_amount'] = np.log1p(df['transaction_amount'])
    return df

def aggregate_and_summarize(df):
    # Customer-Level Aggregation
    df['total_deposits'] = df.groupby('account_id')['transaction_amount'].transform('sum')
    df['average_withdrawals'] = df.groupby('account_id')['transaction_amount'].transform('mean')
    
    # Time-Series Resampling
    df.set_index('timestamp', inplace=True)
    df['monthly_transaction_sum'] = df['transaction_amount'].resample('M').sum()
    
    # Rolling Window Calculations
    df['rolling_avg_transaction'] = df['transaction_amount'].rolling(window=30).mean()
    
    return df.reset_index()

def preprocess_text_data(df):
    # Example NLP techniques
    df['transaction_notes_tokenized'] = df['transaction_notes'].apply(lambda x: x.split())
    df['transaction_notes_cleaned'] = df['transaction_notes'].apply(lambda x: ' '.join([word for word in x.split() if word not in stopwords]))
    return df

def transform_data(file_path):
    df = pd.read_csv(file_path)
    df = encode_categorical_variables(df)
    df = scale_numerical_features(df)
    df = log_transform_skewed_data(df)
    df = aggregate_and_summarize(df)
    df = preprocess_text_data(df)
    return df

if __name__ == "__main__":
    transformed_data = transform_data('data/cleaned_transactions.csv')
    transformed_data.to_csv('data/transformed_transactions.csv', index=False)
