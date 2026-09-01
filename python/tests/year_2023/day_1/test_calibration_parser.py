import pytest

from year_2023.day_1 import calibration_parser


@pytest.mark.parametrize("input,expected", [
    ("1abc2", 12),
    ("pqr3stu8vwx", 38),
    ("a1b2c3d4e5f", 15),
    ("treb7uchet", 77),
])
def test_calibration(input, expected):
    result = calibration_parser.parse(input)
    assert result == expected
