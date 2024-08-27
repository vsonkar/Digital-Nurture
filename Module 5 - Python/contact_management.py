import pickle

def read_contacts_from_text(file_name):
    try:
        with open(file_name, 'r') as file:
            contacts = file.readlines()
            return [contact.strip() for contact in contacts]
    except FileNotFoundError:
        return []

def write_contacts_to_text(file_name, contacts):
    with open(file_name, 'w') as file:
        for contact in contacts:
            file.write(contact + '\n')

def add_contact(file_name, contact):
    contacts = read_contacts_from_text(file_name)
    contacts.append(contact)
    write_contacts_to_text(file_name, contacts)

def remove_contact(file_name, contact):
    contacts = read_contacts_from_text(file_name)
    if contact in contacts:
        contacts.remove(contact)
        write_contacts_to_text(file_name, contacts)

def display_contacts(file_name):
    contacts = read_contacts_from_text(file_name)
    if contacts:
        for contact in contacts:
            print(contact)
    else:
        print("No contacts found.")

def save_contacts_to_binary(file_name, contacts):
    with open(file_name, 'wb') as file:
        pickle.dump(contacts, file)

def load_contacts_from_binary(file_name):
    try:
        with open(file_name, 'rb') as file:
            contacts = pickle.load(file)
            return contacts
    except (FileNotFoundError, EOFError, pickle.UnpicklingError):
        return []

def main():
    file_name_text = 'contacts.txt'
    file_name_binary = 'contacts.dat'

    while True:
        print("\nContact Management System")
        print("1. Add Contact")
        print("2. Remove Contact")
        print("3. Display Contacts (Text)")
        print("4. Display Contacts (Binary)")
        print("5. Exit")

        choice = input("Enter your choice: ")

        if choice == '1':
            contact = input("Enter contact name: ")
            add_contact(file_name_text, contact)
            contacts = load_contacts_from_binary(file_name_binary)
            contacts.append(contact)
            save_contacts_to_binary(file_name_binary, contacts)

        elif choice == '2':
            contact = input("Enter contact name to remove: ")
            remove_contact(file_name_text, contact)
            contacts = load_contacts_from_binary(file_name_binary)
            if contact in contacts:
                contacts.remove(contact)
                save_contacts_to_binary(file_name_binary, contacts)

        elif choice == '3':
            display_contacts(file_name_text)

        elif choice == '4':
            contacts = load_contacts_from_binary(file_name_binary)
            if contacts:
                for contact in contacts:
                    print(contact)
            else:
                print("No contacts found in binary file.")

        elif choice == '5':
            break

        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()
