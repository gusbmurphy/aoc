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


class FoundValueAndIndex(NamedTuple):
    value: str | int
    index: int


class FirstAndLastFoundNumbers:
    _first: FoundValueAndIndex | None = None
    _last: FoundValueAndIndex | None = None

    def register_found(self, found: FoundValueAndIndex):
        if not self._first:
            self._first = found
        elif found.index < self._first.index:
            self._first = found

        if not self._last:
            self._last = found
        elif found.index > self._last.index:
            self._last = found

    def full_value(self) -> int:
        if self._first is None or self._last is None:
            raise Exception(f'Could not find digits')

        return int(str(self._first.value) + str(self._last.value))


def parse(calibration_string: str) -> int:
    found = FirstAndLastFoundNumbers()

    for i in range(len(calibration_string)):
        character = calibration_string[i]
        if character.isdigit():
            found.register_found(FoundValueAndIndex(character, i))
        else:
            found_number_string: NumberString | None = None
            for number_string in _NUMBER_STRINGS:
                if found_number_string:
                    break

                index_of_string = number_string.index_in(calibration_string)

                if index_of_string == i:
                    found_number_string = number_string

            if found_number_string:
                found.register_found(FoundValueAndIndex(found_number_string.value, i))

    return found.full_value()
