import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np

def summary_statistics(df):
    desc_stats = df.describe()
    print("Summary Statistics:\n", desc_stats)
    return desc_stats

def transaction_value_distribution(df):
    plt.figure(figsize=(10, 6))
    sns.histplot(df['transaction_amount'], kde=True)
    plt.title('Transaction Value Distribution')
    plt.xlabel('Transaction Amount')
    plt.ylabel('Frequency')
    plt.show()

    plt.figure(figsize=(10, 6))
    sns.boxplot(x=df['transaction_amount'])
    plt.title('Transaction Value Box Plot')
    plt.xlabel('Transaction Amount')
    plt.show()

def client_segmentation_analysis(df):
    segment_labels = ['Low-Risk', 'Medium-Risk', 'High-Risk']
    df['client_segment'] = pd.qcut(df['risk_score'], q=3, labels=segment_labels)
    print("Client Segmentation:\n", df['client_segment'].value_counts())

def visualization_techniques(df):
    plt.figure(figsize=(10, 6))
    sns.histplot(df['transaction_amount'], kde=True)
    plt.title('Histogram of Transaction Amounts')
    plt.xlabel('Transaction Amount')
    plt.ylabel('Frequency')
    plt.show()

    plt.figure(figsize=(10, 6))
    sns.boxplot(x=df['transaction_amount'])
    plt.title('Box Plot of Transaction Amounts')
    plt.xlabel('Transaction Amount')
    plt.show()

    plt.figure(figsize=(10, 6))
    sns.scatterplot(x='transaction_frequency', y='transaction_amount', data=df)
    plt.title('Scatter Plot of Transaction Frequency vs. Amount')
    plt.xlabel('Transaction Frequency')
    plt.ylabel('Transaction Amount')
    plt.show()

    plt.figure(figsize=(12, 8))
    sns.heatmap(df.corr(), annot=True, cmap='coolwarm')
    plt.title('Heatmap of Correlations')
    plt.show()

def anomaly_detection_eda(df):
    df['transaction_diff'] = df.groupby('account_id')['transaction_amount'].diff()
    df['transaction_diff'].hist(bins=50)
    plt.title('Histogram of Transaction Differences')
    plt.xlabel('Transaction Difference')
    plt.ylabel('Frequency')
    plt.show()

    df['time_series'] = pd.to_datetime(df['timestamp'])
    df.set_index('time_series', inplace=True)
    df['transaction_amount'].plot()
    plt.title('Time-Series of Transaction Amounts')
    plt.xlabel('Time')
    plt.ylabel('Transaction Amount')
    plt.show()

def correlation_and_feature_selection(df):
    corr_matrix = df.corr()
    print("Correlation Matrix:\n", corr_matrix)
    
    from sklearn.feature_selection import SelectKBest, f_classif
    X = df.drop('is_fraud', axis=1)
    y = df['is_fraud']
    selector = SelectKBest(score_func=f_classif, k='all')
    selector.fit(X, y)
    feature_scores = pd.DataFrame(selector.scores_, index=X.columns, columns=['Score'])
    feature_scores = feature_scores.sort_values(by='Score', ascending=False)
    print("Feature Importance Ranking:\n", feature_scores)

def behavioral_profiling(df):
    df['transaction_velocity'] = df.groupby('account_id')['transaction_amount'].transform(lambda x: x.diff().abs())
    df['transaction_velocity'].hist(bins=50)
    plt.title('Histogram of Transaction Velocity')
    plt.xlabel('Transaction Velocity')
    plt.ylabel('Frequency')
    plt.show()

    cross_border_transactions = df[df['cross_border'] == True]
    cross_border_transactions['transaction_amount'].hist(bins=50)
    plt.title('Histogram of Cross-Border Transaction Amounts')
    plt.xlabel('Transaction Amount')
    plt.ylabel('Frequency')
    plt.show()

def exploratory_data_analysis(file_path):
    df = pd.read_csv(file_path)
    summary_statistics(df)
    transaction_value_distribution(df)
    client_segmentation_analysis(df)
    visualization_techniques(df)
    anomaly_detection_eda(df)
    correlation_and_feature_selection(df)
    behavioral_profiling(df)

if __name__ == "__main__":
    exploratory_data_analysis('data/processed_transactions.csv')
