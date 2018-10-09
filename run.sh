set -e

cd ./preprocessor
bash run.sh

cd ..
cd ./runtime/predictor
bash run.sh