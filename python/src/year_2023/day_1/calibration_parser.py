from typing import NamedTuple


class NumberString(NamedTuple):
    string: str
    value: int

    def index_in(self, target: str) -> int | None:
        try:
            return target.index(self.string)
        except ValueError:
            return None


_NUMBER_STRINGS = (
    NumberString("one", 1),
    NumberString("two", 2),
    NumberString("three", 3),
    NumberString("four", 4),
    NumberString("five", 5),
    NumberString("six", 6),
    NumberString("seven", 7),
    NumberString("eight", 8),
    NumberString("nine", 9),
)


def parse(calibration_string: str) -> int:
    first_number: str | None = None
    last_number: str | None = None

    i = 0
    while i < len(calibration_string):
        character = calibration_string[i]
        if character.isdigit():
            if first_number is None:
                first_number = character
            last_number = character
            i += 1
        else:
            found_number_string: NumberString | None = None
            for number_string in _NUMBER_STRINGS:
                if found_number_string:
                    break

                index_of_string = number_string.index_in(calibration_string)

                if index_of_string == i:
                    found_number_string = number_string

            if found_number_string:
                if first_number is None:
                    first_number = str(found_number_string.value)
                last_number = str(found_number_string.value)
                i += len(found_number_string.string)
            else:
                i += 1

    if first_number is None or last_number is None:
        raise Exception(f'Missing digits in string "{calibration_string}"')

    return int(first_number + last_number)
