import pandas as pd
from sklearn.ensemble import IsolationForest
from sklearn.cluster import KMeans, DBSCAN
from sklearn.preprocessing import StandardScaler
from keras.models import Model, load_model
from keras.layers import Input, Dense
import joblib

def load_data(file_path):
    df = pd.read_csv(file_path)
    return df

def autoencoder(df):
    X = df.drop('is_fraud', axis=1)
    input_dim = X.shape[1]
    input_layer = Input(shape=(input_dim,))
    encoder = Dense(32, activation='relu')(input_layer)
    decoder = Dense(input_dim, activation='sigmoid')(encoder)
    autoencoder = Model(inputs=input_layer, outputs=decoder)
    autoencoder.compile(optimizer='adam', loss='mean_squared_error')
    autoencoder.fit(X, X, epochs=50, batch_size=32, shuffle=True, validation_split=0.2)
    autoencoder.save('models/autoencoder.h5')

def isolation_forest(df):
    X = df.drop('is_fraud', axis=1)
    model = IsolationForest(contamination=0.01)
    model.fit(X)
    df['anomaly'] = model.predict(X)
    joblib.dump(model, 'models/isolation_forest.pkl')
    print(df['anomaly'].value_counts())

def clustering(df):
    X = df.drop('is_fraud', axis=1)
    scaler = StandardScaler()
    X_scaled = scaler.fit_transform(X)
    
    kmeans = KMeans(n_clusters=3)
    df['kmeans_labels'] = kmeans.fit_predict(X_scaled)
    joblib.dump(kmeans, 'models/kmeans.pkl')
    print(df['kmeans_labels'].value_counts())
    
    dbscan = DBSCAN(eps=0.5, min_samples=10)
    df['dbscan_labels'] = dbscan.fit_predict(X_scaled)
    joblib.dump(dbscan, 'models/dbscan.pkl')
    print(df['dbscan_labels'].value_counts())

if __name__ == "__main__":
    df = load_data('data/processed_transactions.csv')
    autoencoder(df)
    isolation_forest(df)
    clustering(df)
