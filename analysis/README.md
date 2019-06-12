# Prepare the environment

```
pip3 install -r requirements.txt
```

You will probably need `sudo`, or better, use an virtual environment:

```
python3 -m venv env
source env/bin/activate
pip install -r requirements.txt
```

# Generating some data

Run `MultiTest.java`.

# Running the analysis

```
source env/bin/activate  # if using virtual env and not already activated
jupyter notebook
```

Now, open the browser and go to `localhost:8888`.
