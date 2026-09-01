def parse(calibration_string: str) -> int:
    first_number: str | None = None
    last_number: str | None = None

    for character in calibration_string:
        if character.isdigit():
            if first_number is None:
                first_number = character
            last_number = character

    if first_number is None or last_number is None:
        raise Exception(f'Missing digits in string "{calibration_string}"')

    return int(first_number + last_number)