#version 330 core

in vec2 passTextureCoord;

out vec4 outColor;

uniform sampler2D tex;

void main() {
	outColor = texture(tex, passTextureCoord);

//	if (colorr_.a == 0.0) {
//	    discard;
//	}
}
