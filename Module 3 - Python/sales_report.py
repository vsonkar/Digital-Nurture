def generate_report(sales):
    total_sales = 0
    
    for sale in sales:
        if sale>500:
            print(f"Sale amount: ${sale} - *Over $500!*")
        else:
            print(f"Sale amount: ${sale}")
        
        total_sales+=sale
        
    print(f"\nTotal sales for the month: ${total_sales}")
        

sales = [200, 600, 150, 800, 300]
generate_report(sales)