from pathlib import Path

from year_2023.day_1 import calibration_parser


def parse_file(path: Path) -> int:
    with open(path) as file:
        total: int = 0
        for line in file.readlines():
            total += calibration_parser.parse(line)

        return total
