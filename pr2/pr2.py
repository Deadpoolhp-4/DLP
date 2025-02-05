def create_finite_automaton():

    num_symbols = int(input("Enter the number of input symbols: "))
    symbols = input(f"Enter the {num_symbols} input symbols separated by space: ").split()

    num_states = int(input("Enter the number of states: "))
    initial_state = int(input("Enter the initial state: ")) - 1  

    num_accepting_states = int(input("Enter the number of accepting states: "))
    accepting_states = set(map(lambda x: int(x) - 1, input(f"Enter {num_accepting_states} accepting states separated by space: ").split()))  # Convert to 0-based index

    transitions = [[-1 for _ in range(num_symbols)] for _ in range(num_states)]

    print("Enter the state transitions in the format: <from_state> <input_symbol> <to_state>")
    for _ in range(num_states * num_symbols):
        from_state, symbol, to_state = input().split()
        from_state = int(from_state) - 1  
        to_state = int(to_state) - 1  
        symbol_index = symbols.index(symbol)
        transitions[from_state][symbol_index] = to_state

    return transitions, num_states, initial_state, accepting_states, symbols

def validate_string(automaton, input_string):
    transitions, num_states, initial_state, accepting_states, symbols = automaton
    current_state = initial_state
    
    for symbol in input_string:
        if symbol not in symbols:
            print(f"Invalid symbol '{symbol}' in the input string.")
            return False
        symbol_index = symbols.index(symbol)
        next_state = transitions[current_state][symbol_index]
        if next_state == -1:
            print(f"No transition from state {current_state + 1} on symbol '{symbol}'.")
            return False
        current_state = next_state

    if current_state in accepting_states:
        print("The string is accepted.")
        return True
    else:
        print("The string is not accepted.")
        return False

def main():

    automaton = create_finite_automaton()

    input_string = input("Enter the string to validate: ")

    validate_string(automaton, input_string)

if __name__ == "__main__":
    main()