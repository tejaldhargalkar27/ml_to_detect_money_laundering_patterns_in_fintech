import pandas as pd
from sklearn.ensemble import IsolationForest
from sklearn.neighbors import LocalOutlierFactor
from sklearn.preprocessing import StandardScaler

def detect_outliers(df):
    # Z-Score Method
    df['z_score'] = (df['transaction_amount'] - df['transaction_amount'].mean()) / df['transaction_amount'].std()
    df['is_outlier_z_score'] = df['z_score'].abs() > 3
    
    # IQR Method
    Q1 = df['transaction_amount'].quantile(0.25)
    Q3 = df['transaction_amount'].quantile(0.75)
    IQR = Q3 - Q1
    df['is_outlier_iqr'] = (df['transaction_amount'] < (Q1 - 1.5 * IQR)) | (df['transaction_amount'] > (Q3 + 1.5 * IQR))
    
    return df

def ml_based_outlier_detection(df):
    # Isolation Forest
    iso_forest = IsolationForest(contamination=0.01)
    df['is_outlier_iso_forest'] = iso_forest.fit_predict(df[['transaction_amount']])
    
    # Local Outlier Factor
    lof = LocalOutlierFactor(n_neighbors=20, contamination=0.01)
    df['is_outlier_lof'] = lof.fit_predict(df[['transaction_amount']])
    
    return df

def handle_outliers(df):
    df = detect_outliers(df)
    df = ml_based_outlier_detection(df)
    
    # Handling outliers
    df.loc[df['is_outlier_z_score'] | df['is_outlier_iqr'] | (df['is_outlier_iso_forest'] == -1) | (df['is_outlier_lof'] == -1), 'transaction_amount'] = df['transaction_amount'].median()
    return df

def process_outliers(file_path):
    df = pd.read_csv(file_path)
    df = handle_outliers(df)
    return df

if __name__ == "__main__":
    processed_data = process_outliers('data/aggregated_transactions.csv')
    processed_data.to_csv('data/processed_transactions.csv', index=False)
