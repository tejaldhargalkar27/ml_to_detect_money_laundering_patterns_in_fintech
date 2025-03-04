import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier
from sklearn.metrics import classification_report, confusion_matrix, accuracy_score
import joblib

def load_data(file_path):
    df = pd.read_csv(file_path)
    X = df.drop('is_fraud', axis=1)
    y = df['is_fraud']
    return train_test_split(X, y, test_size=0.3, random_state=42)

def logistic_regression(X_train, X_test, y_train, y_test):
    model = LogisticRegression()
    model.fit(X_train, y_train)
    predictions = model.predict(X_test)
    print("Logistic Regression:\n", classification_report(y_test, predictions))
    joblib.dump(model, 'models/logistic_regression.pkl')

def decision_tree(X_train, X_test, y_train, y_test):
    model = DecisionTreeClassifier()
    model.fit(X_train, y_train)
    predictions = model.predict(X_test)
    print("Decision Tree:\n", classification_report(y_test, predictions))
    joblib.dump(model, 'models/decision_tree.pkl')

def random_forest(X_train, X_test, y_train, y_test):
    model = RandomForestClassifier(n_estimators=100)
    model.fit(X_train, y_train)
    predictions = model.predict(X_test)
    print("Random Forest:\n", classification_report(y_test, predictions))
    joblib.dump(model, 'models/random_forest.pkl')

def gradient_boosting(X_train, X_test, y_train, y_test):
    model = GradientBoostingClassifier(n_estimators=100)
    model.fit(X_train, y_train)
    predictions = model.predict(X_test)
    print("Gradient Boosting:\n", classification_report(y_test, predictions))
    joblib.dump(model, 'models/gradient_boosting.pkl')

def neural_network(X_train, X_test, y_train, y_test):
    from keras.models import Sequential
    from keras.layers import Dense
    model = Sequential()
    model.add(Dense(64, input_dim=X_train.shape[1], activation='relu'))
    model.add(Dense(32, activation='relu'))
    model.add(Dense(1, activation='sigmoid'))
    model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])
    model.fit(X_train, y_train, epochs=10, batch_size=10, verbose=1)
    predictions = (model.predict(X_test) > 0.5).astype(int)
    print("Neural Network:\n", classification_report(y_test, predictions))
    model.save('models/neural_network.h5')

if __name__ == "__main__":
    X_train, X_test, y_train, y_test = load_data('data/processed_transactions.csv')
    logistic_regression(X_train, X_test, y_train, y_test)
    decision_tree(X_train, X_test, y_train, y_test)
    random_forest(X_train, X_test, y_train, y_test)
    gradient_boosting(X_train, X_test, y_train, y_test)
    neural_network(X_train, X_test, y_train, y_test)
