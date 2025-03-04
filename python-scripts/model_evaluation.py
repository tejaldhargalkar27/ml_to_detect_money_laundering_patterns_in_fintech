import pandas as pd
from sklearn.metrics import precision_score, recall_score, f1_score, roc_auc_score, confusion_matrix, classification_report
from sklearn.model_selection import GridSearchCV, StratifiedKFold
import shap
import lime
import lime.lime_tabular
import joblib
import numpy as np

def load_data(file_path):
    df = pd.read_csv(file_path)
    X = df.drop('is_fraud', axis=1)
    y = df['is_fraud']
    return X, y

def evaluate_model(model, X_test, y_test):
    predictions = model.predict(X_test)
    probs = model.predict_proba(X_test)[:, 1]
    
    precision = precision_score(y_test, predictions)
    recall = recall_score(y_test, predictions)
    f1 = f1_score(y_test, predictions)
    auc = roc_auc_score(y_test, probs)
    cm = confusion_matrix(y_test, predictions)
    
    print("Precision: ", precision)
    print("Recall: ", recall)
    print("F1-Score: ", f1)
    print("AUC-ROC: ", auc)
    print("Confusion Matrix:\n", cm)
    print("Classification Report:\n", classification_report(y_test, predictions))

def model_calibration(model, X_train, y_train):
    param_grid = {
        'n_estimators': [50, 100, 200],
        'max_depth': [None, 10, 20, 30],
        'min_samples_split': [2, 5, 10],
        'min_samples_leaf': [1, 2, 4]
    }
    grid_search = GridSearchCV(estimator=model, param_grid=param_grid, cv=3, n_jobs=-1, verbose=2)
    grid_search.fit(X_train, y_train)
    return grid_search.best_estimator_

def cross_validation(model, X, y):
    skf = StratifiedKFold(n_splits=5)
    for train_index, test_index in skf.split(X, y):
        X_train, X_test = X.iloc[train_index], X.iloc[test_index]
        y_train, y_test = y.iloc[train_index], y.iloc[test_index]
        model.fit(X_train, y_train)
        evaluate_model(model, X_test, y_test)

def explainability_shap(model, X):
    explainer = shap.TreeExplainer(model)
    shap_values = explainer.shap_values(X)
    shap.summary_plot(shap_values, X)

def explainability_lime(model, X_train, X_test, y_train):
    explainer = lime.lime_tabular.LimeTabularExplainer(X_train.values, feature_names=X_train.columns, class_names=['Not Fraud', 'Fraud'], discretize_continuous=True)
    i = np.random.randint(0, len(X_test))
    exp = explainer.explain_instance(X_test.values[i], model.predict_proba, num_features=10)
    exp.show_in_notebook(show_table=True)

def real_world_testing(model, X_test):
    # Simulated transaction scenarios
    simulated_data = X_test.sample(10)
    predictions = model.predict(simulated_data)
    print("Simulated Transaction Predictions:\n", predictions)

    # A/B Testing with compliance teams would be implemented in the deployment phase

if __name__ == "__main__":
    X, y = load_data('data/processed_transactions.csv')
    
    # Load a pre-trained model
    model = joblib.load('models/random_forest.pkl')
    
    # Evaluate the model
    evaluate_model(model, X, y)
    
    # Model calibration
    best_model = model_calibration(model, X, y)
    joblib.dump(best_model, 'models/best_random_forest.pkl')
    
    # Cross-validation
    cross_validation(best_model, X, y)
    
    # Explainability
    explainability_shap(best_model, X)
    explainability_lime(best_model, X, X, y)
    
    # Real-world testing
    real_world_testing(best_model, X)
