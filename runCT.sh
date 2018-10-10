set -e

cd ./preprocessor
bash run.sh

cd ..
cd ./runtime/predictor
bash runCT.sh "$@"
