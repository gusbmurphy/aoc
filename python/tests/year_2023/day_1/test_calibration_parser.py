from pathlib import Path

import pytest

from year_2023.day_1 import calibration_parser, calibration


@pytest.mark.parametrize("input,expected", [
    ("1abc2", 12),
    ("pqr3stu8vwx", 38),
    ("a1b2c3d4e5f", 15),
    ("treb7uchet", 77),
    ("two1nine", 29),
    ("xtwone3four", 24),
    ("zoneight234", 14),
])
def test_calibration(input, expected):
    result = calibration_parser.parse(input)
    assert result == expected


def test_example_data():
    example_path = Path(__file__).parent / "example.txt"
    result = calibration.parse_file(example_path)
    assert result == 142


def test_actual():
    example_path = Path(__file__).parent / "input.txt"
    result = calibration.parse_file(example_path)
    assert result == 53974
