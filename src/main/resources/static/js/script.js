function successfullyLogout(){
    alert("You have been successfully logged out");
}

function enforceLength(input) {
    const value = input.value;

    // Rozdzielenie na część całkowitą i ułamkową
    const [integer, decimal] = value.split('.');

    // Maksymalnie 10 cyfr przed przecinkiem
    const maxIntegerLength = 10;
    // Maksymalnie 2 cyfry po przecinku
    const maxDecimalLength = 2;

    let newValue = '';

    if (integer) {
        newValue += integer.slice(0, maxIntegerLength); // Ogranicz część całkowitą
    }

    if (decimal) {
        newValue += '.' + decimal.slice(0, maxDecimalLength); // Ogranicz część ułamkową
    }

    // Aktualizuj wartość inputa
    input.value = newValue;
}