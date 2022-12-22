import argparse
import csv

import matplotlib.pyplot as plt
import numpy as np


def read_file(file_name):
    with open(file_name) as csv_file:
        line_count = 1
        line_reader = csv.reader(csv_file)
        scores = []
        errors = []
        for row in line_reader:
            if line_count == 2:
                for cell in row:
                    scores.append(float(cell))
            elif line_count == 3:
                for cell in row:
                    errors.append(float(cell))
            line_count += 1
        return scores, errors


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Create a bar chart from results.')
    parser.add_argument('-t', '--title', type=str, help='the chart title')
    parser.add_argument('-y', '--y_axis', type=str, help='the y-axis label')
    parser.add_argument('-d', '--directory', type=str, help='the directory containing the scores')
    args = parser.parse_args()

    category_labels = ["POJO", "Immutable", "Default Value", "Value Class"]

    x = np.arange(len(category_labels))

    fig, ax = plt.subplots()

    ax.set_title(args.title)
    ax.set_ylabel(args.y_axis)
    ax.set_xticks(x, category_labels)

    width = 0.2
    capsize = 5

    gson_scores, gson_errors = read_file(f"{args.directory}/GSON.csv")
    gson_plot = ax.bar(x - 0.3, gson_scores, width, label='GSON', yerr=gson_errors, capsize=capsize)
    jackson_scores, jackson_errors = read_file(f"{args.directory}/Jackson.csv")
    jackson_plot = ax.bar(x - 0.1, jackson_scores, width, label='Jackson', yerr=jackson_errors, capsize=capsize)
    jsonb_scores, jsonb_errors = read_file(f"{args.directory}/JSONB.csv")
    jsonb_plot = ax.bar(x + 0.1, jsonb_scores, width, label='JSON-B', yerr=jsonb_errors, capsize=capsize)
    kotlin_scores, kotlin_errors = read_file(f"{args.directory}/Kotlin.csv")
    kotlin_plot = ax.bar(x + 0.3, kotlin_scores, width, label='Kotlin', yerr=kotlin_errors, capsize=capsize)

    ax.legend()
    fig.tight_layout()

    plt.gca().get_yaxis().get_major_formatter().set_scientific(False)
    plt.show()
