from year_2023.day_1 import calibration_parser


def test_numbers_on_either_end():
    result = calibration_parser.parse("1abc2")
    assert result == 12
