import logging
from datetime import datetime

logging.basicConfig(filename='transaction_errors.log', level=logging.ERROR)

def validate_transaction(transaction):
    if 'amount' not in transaction or not isinstance(transaction['amount'], (int, float)):
        raise ValueError("Invalid transaction: 'amount' must be a number.")
    if transaction['amount'] <= 0:
        raise ValueError("Invalid transaction: 'amount' must be positive.")

def log_error(error_message):
    timestamp = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    logging.error(f"{timestamp} - {error_message}")

def process_transaction(transaction):
    try:
        validate_transaction(transaction)
        print(f"Processing transaction: {transaction}")

    except ValueError as ve:
        log_error(str(ve))
        print(f"Error: {ve}. Please try again.")

    except Exception as e:
        log_error(str(e))
        print(f"An unexpected error occurred: {e}. Please try again.")

def main():
    while True:
        try:
            amount = float(input("Enter transaction amount: "))
            transaction = {'amount': amount}
            process_transaction(transaction)

        except ValueError:
            print("Please enter a valid number.")

        continue_processing = input("Do you want to process another transaction? (yes/no): ")
        if continue_processing.lower() != 'yes':
            break

if __name__ == "__main__":
    main()
