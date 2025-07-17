# Problema 1
![[Pasted image 20250714171651.png|center|500]]

![[Pasted image 20250714171708.png|center|500]]

dove $\overrightarrow{F_{d}}$ è la forza di attrito dinamico, $\overrightarrow{F}$ è la forza applicata, $\overrightarrow{N}$ è la reazione vincolare del piano, $m\overrightarrow{g}$ è la forza peso.

## a.) Modulo di $\overrightarrow{N}$ e $\overrightarrow{F}$ 
Da ipotesi, la cassa si muove di moto rettilineo uniforme, quindi la risultante delle forze agenti sul corpo è nulla $$\overrightarrow{F}+m\overrightarrow{g}+\overrightarrow{F_{d}}+\overrightarrow{N}=0$$
Otteniamo quindi le componenti $$\begin{align}
&F_{x}=F\cos\Theta\qquad &&F_{y}=F\sin\Theta\\\\
&(m \overrightarrow{g})_{x}=0\qquad &&(m \overrightarrow{g})_{y}=-mg\\\\
&N_{x}=0\qquad &&N_{y}= |\overrightarrow{N}|=N\\\\
&F_{dx}=-\mu_{d}N\qquad &&F_{dy}=0
\end{align}$$
Otteniamo quindi il sistema di equazioni $$\begin{cases}F_{x}+(m \overrightarrow{g})_{x}+ F_{dx}+ N_{x}=0\\\\F_{y}+(m \overrightarrow{g})_{y}+ F_{dy}+ N_{y}=0\end{cases}\implies \begin{cases}F\cos\Theta-\mu_{d} N=0\\\\ F\sin\Theta-mg+N=0\end{cases}$$
risolviamo il sistema nelle incognite $F$ e $N$ $$\begin{align}&\begin{cases}F\cos\Theta-\mu_{d}(-F\sin\Theta+mg)=0\\\\N=-F\sin\Theta+mg\end{cases}\\\\ &\begin{cases}F\cos\Theta+\mu_{d}F\sin\Theta-\mu_{d}mg=0\\\\N=-F\sin\Theta+mg\end{cases}\\\\
&\begin{cases}F(\cos\Theta+\mu_{d}\sin\Theta)=\mu_{d}mg\\\\N=-F\sin\Theta+mg\end{cases}\end{align}$$
Ottengo quindi le due equazioni $$F=\frac{\mu_{d}mg}{\cos\Theta+\mu_{d}\sin\Theta}$$
e $$\begin{align}N&=-\frac{\mu_{d}mg\sin\Theta}{\cos\Theta+\mu_{d}\sin\Theta}+mg=mg\bigg[1-\frac{\mu_{d}\cancel{mg}\sin\Theta}{\cos\Theta+\mu_{d}\sin\Theta}\cdot \cancel{mg}\bigg]=\\\\
&=mg\bigg[\frac{\cos\Theta+\cancel{\mu_{d}\sin\Theta}-\cancel{\mu_{d}\sin\Theta}}{\cos\Theta+\mu_{d}\sin\Theta}\bigg]=\frac{mg\cos\Theta}{\cos\Theta+\mu_{d}\sin\Theta}\end{align}$$
Sostituendo i valori di $\Theta=30°$ (da ipotesi) otteniamo $$\begin{align}&F=\frac{0.3\cdot(30kg)\cdot\bigg(9.81 \frac{m}{s^{2}}\bigg)}{\cos30°+0.3\sin30°}\simeq86.90N\\\\
&N=\frac{(30kg)\cdot\bigg(9.81 \frac{m}{s^{2}}\cdot\cos30°\bigg)}{\cos30°+0.3\sin30°}\simeq250.85N\end{align}$$
## b.) Valore massimo $\overrightarrow{F}$ tale che la cassa non si sollevi
Per far si che la cassa non si sollevi, la reazione vincolare deve risultare positiva, ovvero $$N\geq0$$
Sull'asse $y$ vale sempre la condizione $$N=-F\sin\Theta+mg$$ e per valere $N\geq0$ deve valere $$-F\sin\Theta+mg\geq0\implies F\sin\Theta\leq mg\implies F\leq \frac{mg}{\sin\Theta}$$
Il valore per cui $F$ risulta massima lo otteniamo quando $N=0$ ovvero quando $$F_{M}=\frac{mg}{\sin\Theta}=\frac{(30kg)\cdot\bigg(9.81 \frac{m}{s^{2}}\bigg)}{\sin30°}\simeq588.60N$$
## c.) Valore di $\Theta$ che minimizza $F$
Nel punto a.) abbiamo ottenuto che nel caso di moto rettilineo uniforme risulta $$F=\frac{\mu_{d}mg}{\cos\Theta+\mu_{d}\sin\Theta}$$
Fissati $m$ e $\mu_{d}$ cerchiamo per quali valori di $\Theta$ esiste un minimo per $F$, calcolando la sua derivata $$\frac{dF}{d\Theta}=\frac{-\mu_{d}mg\cdot(-sin\Theta+\mu_{d}\cos\Theta)}{(\cos\Theta+\mu_{d}\sin\Theta)^{2}}=\frac{\mu_{d}mg\cdot(\sin\Theta-\mu_{d}\cos\Theta)}{(\cos\Theta+\mu_{d}\sin\Theta)^{2}}$$
La derivata risulta $\geq 0$ per $$\sin\Theta-\mu_{d}\cos\Theta\geq 0\implies\sin\Theta\geq \mu_{d}\cos\Theta \implies \tan\Theta\geq \mu_{d}$$
da cui otteniamo $$\Theta=\Theta_{min}\geq\arctan\mu_{d}$$
Il diagramma del segno della derivata è il seguente ![[Pasted image 20250714184028.png|center|500]]
La funzione $F(\Theta)$ ha quindi un minimo per $\Theta=\bar\Theta$ che vale $$\bar \Theta=\arctan \mu_{d}=\arctan0.3\simeq0.291 rad\simeq16.7°$$ (calcolare eventualmente $F_{min}$ sostituendo $\bar\Theta$ nella formula precedente $F_{min}\simeq84.57N$)

# Problema 2
![[Pasted image 20250714184401.png|center|500]]
![[Pasted image 20250715151027.png|center|300]]

## a.) Calcolo della velocità angolare $\omega_{f}$
Dopo il contatto tra i due cilindri il momento totale delle forze agenti sul sistema è nullo, per cui il momento angolare totale del sistema si conserva nel contatto e in particolare si conserva la componente del momento angolare lungo l'asse comune dei due cilindri. 

Osserviamo che il volume del cilindro più piccolo è $$V_{2}=\pi r^{2}h=\pi\bigg(\frac{R}{2}\bigg)^{2}\cdot\bigg(\frac{H}{2}\bigg)=\frac{\pi R^{2}H}{8}=\frac{V_{1}}{8}$$ $V_{1}$ è il volume del cilindro più grande. 
Essendo i due cilindri dello stesso materiale, le loro masse e volumi sono nello stesso rapporto, quindi $$m_{2}=\frac{m_{1}}{8}=\frac{M}{8}$$
Dalla conservazione della componente del momento angolare lungo l'asse di rotazione otteniamo la relazione $$I_{zf}\omega_{f}=I_{zi}\omega_{i}$$
Inizialmente ruota solo il primo cilindro, quindi risulta $$I_{zi}=\frac{1}{2}MR^{2}$$
Dopo il contatto i due cilindri ruotano attorno l'asse di rotazione comune con la stessa velocità angolare, quindi risulta $$\begin{align}I_{zf}=I_{1}+I_{2}&=\frac{1}{2}MR^{2}+ \frac{1}{2}m_{2}r^{2}=\frac{1}{2}MR^{2}+ \frac{1}{2} \frac{M}{8} \frac{R^{2}}{4}=\\\\&= \frac{1}{2}MR^{2}\bigg(1+ \frac{1}{8\cdot4}\bigg)= \frac{1}{2}MR^{2}\bigg(1+ \frac{1}{32}\bigg)=\\\\&=\frac{1}{2}MR^{2}\bigg(\frac{33}{32}\bigg)=\frac{33}{64}MR^{2}\end{align}$$
Ricaviamo quindi $$\begin{align}\omega_{f}&=\frac{I_{zi}\omega_{i}}{I_{zf}}=\frac{\frac{1}{2}\cancel{MR^{2}}\omega_{i}}{\frac{33}{64}\cancel{MR^{2}}}=\frac{1}{2}\omega_{i}\cdot \frac{64}{33}=\frac{32}{33}\omega_{i}\\\\&=\frac{32}{33}\bigg(2 \frac{rad}{s}\bigg)=1.939 \frac{rad}{s}\end{align}$$
## b.) Calcolo energia cinetica del sistema
Dopo il contatto l'energia cinetica del sistema dei due cilindri è $$\begin{align}K_{f}&=\frac{1}{2}I_{zf}\omega_{f}^{2}=\frac{1}{2} \frac{33}{64}MR^{2}\cdot \bigg(\frac{32}{33}\bigg)^{2}\omega_{i}^{2}=\\\\
&=\frac{1}{\cancel2} \frac{\cancel{33}}{\cancel{64}_{\cancel2}} \frac{\cancel{32}\cdot \cancel{32}^{8}}{33^{\cancel2}}MR^{2}\omega_{i}^{2}=\frac{8}{33}MR^{2}\omega_{i}=\\\\
&=\frac{8}{33}(1kg)(0.2m)^{2}\bigg(2 \frac{rad}{s}\bigg)^{2}=3.879\cdot10^{-2}J\end{align}$$
## c.) Calcolo lavoro svolto
Il lavoro svolto dalle forze impulsive durante il contatto, per il teorema dell'energia cinetica è $$W=K_{f}-K_{i}$$
$$\begin{align}K_{i}&=\frac{1}{2}I_{zi}\omega_{i}^{2}=\frac{1}{2}\cdot \frac{1}{2}MR^{2}\omega_{i}^{2}= \frac{1}{4}MR^{2}\omega_{i}^{2}=\end{align}$$
$$\begin{align}W&=\bigg(\frac{8}{33}- \frac{1}{4}\bigg)MR^{2}\omega_{i}^{2}=\frac{32-33}{132}MR^{2}\omega_{i}^{2}=\\\\&=-\frac{1}{132}MR^{2}\omega_{i}^{2}=- \frac{1}{132}(1kg)(0.2m)^{2}\bigg(2 \frac{rad}{s}\bigg)^{2}=-1.212\cdot10^{-3}J\end{align}$$
# Problema 3
![[Pasted image 20250714184505.png|center|500]]

![[Pasted image 20250714184524.png|center|500]]

## a.) Corrente $i$ a regime e differenza di potenziale (interruttore chiuso)
Col circuito a regime, i condensatori sono carichi, quindi la corrente passa solo nei resistori. Per la legge di Kirchhoff delle maglie risulta $$\begin{align}\mathcal{E}-R_{1}i-R_{2}i=0\implies i(R_{1}+R_{2})=\mathcal{E}\\\\i=\frac{\mathcal{E}}{R_{1}+R_{2}}=\frac{150V}{50\Omega+100\Omega}=\frac{150V}{150\Omega}=1A\end{align}$$
La differenza di potenziale ai capi di $R_{2}$ è $$V_{2}=\mathcal{E}-R_{1}i=150V-50\Omega\cdot1A=150V-50V=100V$$
## b.) Energia potenziale elettrostatica immagazinata nei due condensatori
A regime i condensatori sono carichi e da costruzione del circuito la differenza di poteniale tra le armature di $C_{1}$ equivale a quella tra le armature di $C_{2}$, quindi $$\begin{align}U=U_{1}+U_{2}&=\frac{1}{2}C_{1}V_{2}^{2}+\frac{1}{2}C_{2}V_{2}^{2}=\frac{1}{2}(C_{1}+C_{2})V_{2}^{2}\\\\&=\frac{1}{2}[(20+18)\cdot10^{-9}F]\cdot(100V)^{2}=1.9\cdot10^{-4}J\end{align}$$
## c.) Costante di tempo di scarica
Al momento dell'apertura dell'interruttore, i due condensatori si scaricano attraverso la resistenza $R_{2}$. Il circuito equivale quindi a ![[Pasted image 20250714194458.png|center|500]]

Con $C_{eq}=C_{1}+C_{2}$.
La costante di tempo di ricarica equivale quindi a $$\tau=R_{2}C_{eq}=R_{2}(C_{1}+C_{2})=(100\Omega)[(20+18)\cdot10^{-9}F]=3.8\mu s$$ 