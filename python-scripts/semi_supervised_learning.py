import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.semi_supervised import SelfTrainingClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import classification_report
import networkx as nx
import joblib

def load_data(file_path):
    df = pd.read_csv(file_path)
    X = df.drop('is_fraud', axis=1)
    y = df['is_fraud']
    return train_test_split(X, y, test_size=0.3, random_state=42)

def self_training(X_train, X_test, y_train, y_test):
    base_classifier = DecisionTreeClassifier()
    model = SelfTrainingClassifier(base_classifier)
    model.fit(X_train, y_train)
    predictions = model.predict(X_test)
    print("Self-Training Classifier:\n", classification_report(y_test, predictions))
    joblib.dump(model, 'models/self_training.pkl')

def graph_based_anomaly_detection(file_path):
    df = pd.read_csv(file_path)
    G = nx.from_pandas_edgelist(df, 'account_id', 'counterparty_id', ['transaction_amount'])
    anomalies = [node for node, degree in dict(G.degree()).items() if degree < 2 or degree > 10]
    print("Graph-Based Anomalies:\n", anomalies)

if __name__ == "__main__":
    X_train, X_test, y_train, y_test = load_data('data/processed_transactions.csv')
    self_training(X_train, X_test, y_train, y_test)
    graph_based_anomaly_detection('data/processed_transactions.csv')
